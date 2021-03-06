(ns vtmcharsheet.core
    (:require
     [reagent.core :as r]
     [reagent.dom :as d]
     [clojure.string :as str]
     [vtmcharsheet.data :as data]
     [cljsjs.react-tooltip-lite]))
(def tooltip (aget js/ReactToolTipLite "default"))

;; -------------------------
;; State

(defonce charsheet
  ;; Default values are set here.
  (r/atom {:clan "brujah"
           :generation 13
           :attributes (zipmap data/attributes-ordered data/attributes-defaults)
           :skill-distribution :jack-of-all-trades
           :skills (zipmap data/skills-ordered data/skills-defaults)}))
(defonce page (r/atom 0))

;; -------------------------
;; Utilities

(defn humanize [x] (str/capitalize (str/replace (name x) "-" " ")))

(defn clamp [value min-value max-value] (max (min value max-value) min-value))

(defn count-elems [pred coll]
  (reduce (fn [acc x] (if (pred x) (inc acc) acc)) 0 coll))

(defn pluralize
  "Poor man's single->plural word function."
  ([n word]
   (if (#{\a \o \u} (last word))
     (pluralize n word (str word "es"))
     (pluralize n word (str word "s"))))
  ([n word plural-word]
   (if (== 1 (mod n 10)) word plural-word)))

;; -------------------------
;; Business-logic

(defn reset-attributes
  ([] (reset-attributes
       (zipmap data/attributes-ordered data/attributes-defaults)))
  ([values] (swap! charsheet assoc :attributes values)))
(defn random-attributes []
  (reset-attributes
   (zipmap data/attributes-ordered (shuffle data/attributes-random))))
(defn reset-skills
  ([] (reset-skills (zipmap data/skills-ordered data/skills-defaults)))
  ([values] (swap! charsheet assoc :skills values)))
(defn random-skills []
  ;; Randomly distribute points
  (reset-skills
   (merge
    (zipmap data/skills-ordered data/skills-defaults)
    (zipmap (shuffle data/skills-ordered)
            (map (fn [x] {:value x})
             (shuffle ((:skill-distribution @charsheet) data/skills-random))))))
  ;; Assign free specialties
  (swap!
   charsheet
   (fn [x]
     (assoc x :skills
            (into
             {}
             (map (fn [[k v]]
                    [k
                     (if (and (data/free-specialties k) (pos? (:value v)))
                       (update-in v [:specialty]
                                  #(first
                                    (shuffle
                                     (:specialties (k data/skills)))))
                       v)])
                  (:skills x))))))
  ;; Assign one specialty
  (let [random-skill-for-specialty
        (first
         (shuffle
          (map
           first
           (filter
            (fn [[_ v]] (and (pos? (:value v)) (str/blank? (:specialty v))))
            (:skills @charsheet)))))]
    (swap! charsheet assoc-in [:skills random-skill-for-specialty :specialty]
           (first
            (shuffle
             (:specialties (random-skill-for-specialty data/skills)))))))

;; -------------------------
;; Components

(defn text-input
  "An input with a label and a placeholder."
  [cursor label placeholder]
  [:div.pure-control-group
   [:label {:for label} (humanize label)]
   [:input {:name label :id label :value @cursor
            :placeholder placeholder
            :on-change #(reset! cursor (.. % -target -value))}]])

(defn select-input
  "A select input with a label."
  [cursor label options]
  [:div.pure-control-group
   [:label {:for label} (humanize label)]
   [:select {:id label :value @cursor
             :on-change #(reset! cursor (.. % -target -value))}
    (for [[k v] options]
      ^{:key k} [:option {:value k} v])]])

(defn select-input-with-cb
  "A select input with a label."
  [options cb]
  [:select {:on-change cb}
   (for [[k v] options]
     ^{:key k} [:option {:value k} v])])

(defn circle-with-tooltip
  "A single circle with a tooltip."
  [tooltip-text is-active]
  [:>
   tooltip
   {:content tooltip-text
    :tag-name :span
    :class-name (str/join " " ["circle" (when is-active "active")])
    :tip-content-class-name ""
    :use-default-styles true}])

(defn circle-input
  "A set of circles defining an integer value and buttons to
  increase or decrease it."
  [cursor min-value max-value tooltips]
  [:div.circle-input
   (doall
     (for [v (range 1 (inc max-value))]
       ^{:key v} [circle-with-tooltip (tooltips v) (<= v @cursor)]))
   [:span.pure-button-group.circle-control
    [:button.pure-button
     {:on-click
      #(swap! cursor (fn [x] (clamp (dec (int x)) min-value max-value)))} "-"]
    [:button.pure-button
     {:on-click
      #(swap! cursor (fn [x] (clamp (inc (int x)) min-value max-value)))} "+"]]])

(defn attribute-element
  "A single line for the attribute."
  [k cursor]
  [:<>
   [:div.pure-u-5-24
    [:>
     tooltip
     {:content (get-in data/attributes [k :description])
      :tag-name :span
      :use-default-styles true}
     (humanize k)]]
   [:div.pure-u-19-24 [circle-input cursor 1 5 (k data/attributes)]]])

(defn describe-discipline-skill [m]
  [:div
   [:ul
    (when-let [n (:name m)]
      [:li "Name: " n])
    (when-let [desc (:description m)]
      [:li "Description: " desc])
    (when-let [cost (:cost m)]
      [:li "Cost: " cost])
    (when-let [dp (:dice-pools m)]
      [:li "Dice pools: " dp])
    (when-let [ing (:ingridients m)]
      [:li "Ingridients: " ing])
    (when-let [d (:duration m)]
      [:li "Duration: " d])]])

(defn discipline-element
  "A single line for the discipline."
  [k cursor]
  (let [description
        (zipmap
         (range 1 6)
         (map
          (fn [n]
            (let [level-disciplines ((k data/disciplines) n)]
              (r/as-element
               [:span
                [:span "Level " n " of " (humanize k)]
                (reduce
                 (fn [acc, x] (conj acc (describe-discipline-skill (second x))))
                 [:span]
                 level-disciplines)])))
          (range 1 6)))]
    [:<>
     [:div.pure-u-5-24
      [:>
       tooltip
       {:content (get-in data/disciplines [k :description])
        :tag-name :span
        :use-default-styles true}
       (humanize k)]]
     [:div.pure-u-19-24 [circle-input cursor 0 5 description]]]))

(defn enough-toomuch-meter [current out-of]
  [:<>
   [:span
    {:class [(if (== current out-of) "correct-color" "wrong-color")]}
    "(" current "/" out-of ")"]
   (when (> current out-of) [:span.text-secondary " too much"])
   (when (< current out-of) [:span.text-secondary " not enough"])])

(defn distribution-line
  "Produces line like the following:
  Take three `unit-name` at 3: (2/3)"
  [value attrs validations unit-name]
  (let [valid (validations value)
        number-of-elems (count-elems #{value} attrs)]
    (when (or (not (zero? valid)) (pos? number-of-elems))
      [:<>
       [:span "Take " (or (data/numbers valid) valid) " "
        (pluralize valid unit-name) " at " value ": "
        [enough-toomuch-meter number-of-elems valid]]
       [:br]])))

(defn distribution-validation-text
  [attrs validations unit-name]
  (for [x (reverse (range 1 6))]
    ^{:key x} [distribution-line x attrs validations unit-name]))

(defn specialty-validation-text [skills free-specialties]
  (let [taken-specialties-count
        (count-elems (fn [[k v]] (and (not (data/free-specialties k))
                                      (not (str/blank? (:specialty v)))))
                     skills)
        available-free-specialties
        (filter (fn [[k v]] (and (data/free-specialties k)
                                 (pos? (:value v))))
                skills)
        specialty-taken-without-points
        (some (fn [[k v]] (and (not (str/blank? (:specialty v)))
                               (zero? (int (:value v)))))
              skills)]
    [:<>
     [:span
      "Take any specialty: "
      [enough-toomuch-meter taken-specialties-count 1]]
     [:br]
     (when specialty-taken-without-points
       [:<>
        [:span.wrong-color
         "Only take specialties in skills with spent points in them"]
        [:br]])
     (for [skill available-free-specialties]
       ^{:key (first skill)}
       [:<>
        [:span
         "Take " (humanize (first skill)) " specialty: "
         [enough-toomuch-meter
          (if (str/blank? (:specialty (second skill))) 0 1)
          1]]
        [:br]])]))

(defn skill-element
  "A single line for the skill."
  [k cursor]
  [:<>
   [:div.pure-u-5-24
    [:>
     tooltip
     {:content (get-in data/skills [k :description])
      :tag-name :span
      :use-default-styles true}
     (humanize k)]]
   [:div.pure-u-10-24.pure-form
    (let [specialty-k (str "specialty-" k)]
      [:>
       tooltip
       {:content (str "Examples: "
                      (str/join ", " (get-in data/skills [k :specialties])))
        :tag-name :span
        :use-default-styles true}
       [:input {:style {:font-size "90%"}
                :value (:specialty @cursor)
                :placeholder "Specialty"
                :on-change
                #(swap! cursor assoc :specialty (.. % -target -value))}]])]
   [:div.pure-u-9-24
    [circle-input (r/cursor cursor [:value]) 0 5 (k data/skills)]]])

;; -------------------------
;; Views

(defn intro-page []
  [:div.pure-form.pure-form-aligned
   [:fieldset
    [text-input (r/cursor charsheet [:name]) "name" "Character's name"]
    [text-input (r/cursor charsheet [:player]) "player" "Your real name"]
    [text-input (r/cursor charsheet [:chronicle]) "chronicle"
     "The title of a chronicle"]
    [text-input (r/cursor charsheet [:concept]) "concept"
     "What is your character?"]
    [text-input (r/cursor charsheet [:ambition]) "ambition"
     "What do you want?"]]])

(defn clan-description [clan-info]
  [:div
   [:h2 (:name clan-info)]
   [:div (:description clan-info)]
   [:h3 "Archetypes"]
   [:div
    (for [archetype (:archetypes clan-info)]
      ^{:key (:name archetype)}
      [:div
       [:strong (:name archetype)]
       [:div (:description archetype)]])]
   [:h3 "Disciplines"]
   [:div
    (for [discipline (:disciplines clan-info)]
      ^{:key discipline}
      [:>
       tooltip
       {:content (:description (discipline data/disciplines))
        :tag-name :span
        :use-default-styles true}
       (str (humanize discipline) " ")])]

   [:h3 "Clan bane"]
   [:div (:bane clan-info)]])

(defn clan-page []
  [:div.pure-form.pure-form-aligned
   [:fieldset
    [text-input (r/cursor charsheet [:sire]) "sire" "Name of your sire"]
    [select-input (r/cursor charsheet [:clan]) "clan"
     (map (fn [[k v]] [k (:name v)]) data/clans)]
    [select-input (r/cursor charsheet [:generation]) "generation"
     (map (fn [x] [x x]) (range 10 17))]
    [:div [clan-description ((keyword (:clan @charsheet)) data/clans)]]]])

(defn attributes-page []
  [:div
   [:h2 "Attributes"]
   [:p (distribution-validation-text
        (vals (:attributes @charsheet))
        data/attribute-validations
        "attribute")]
   [:p.pure-button-group
    [:button.pure-button {:on-click #(reset-attributes)} "Reset"]
    [:button.pure-button {:on-click #(random-attributes)} "Random"]]
   [:p "Hover over any element to get a hint."]
   [:div.pure-g
    (for [k data/attributes-ordered]
      ^{:key k} [attribute-element k (r/cursor charsheet [:attributes k])])]])

(defn skills-page []
  (r/with-let [skill-distribution (r/cursor charsheet [:skill-distribution])]
    [:div
     [:h2 "Skills"]
     [:div
      [:p
       "Skill distribution: "
       [:span.pure-button-group
        (doall
         (for [[k v] data/skill-distributions]
           ^{:key k}
           [:button.pure-button
            {:class [(when (= @skill-distribution k) :pure-button-active)]
             :on-click #(reset! skill-distribution k)}
            (:name v)]))]]
      [:p (distribution-validation-text
           (map (fn [[_ v]] (int (:value v))) (:skills @charsheet))
           ((:skill-distribution @charsheet) data/skill-validations)
           "skill")]
      [:p [specialty-validation-text
           (:skills @charsheet) data/free-specialties]]]
     [:p.pure-button-group
      [:button.pure-button {:on-click #(reset-skills)} "Reset"]
      [:button.pure-button {:on-click #(random-skills)} "Random"]]
     [:p "Hover over any element to get a hint."]
     [:div
      (for [k data/skills-ordered]
        ^{:key k} [skill-element k (r/cursor charsheet [:skills k])])]]))

(defn disciplines-page []
  (let [available-disciplines
        (:disciplines ((keyword (:clan @charsheet)) data/clans))]
    (swap! charsheet assoc :disciplines
           (select-keys (:disciplines @charsheet) available-disciplines))
    [:div
     [:h2 "Disciplines"]
     [:p (distribution-validation-text
          (map (comp :level second)
               (filter (fn [[k _]] (available-disciplines k))
                       (:disciplines @charsheet)))
          data/discipline-validations
          "discipline")]
     [:div.pure-g.pure-form
      (doall
       (for [k available-disciplines
             :let [level-cursor (r/cursor charsheet [:disciplines k :level])
                   powers-cursor (r/cursor charsheet [:disciplines k :powers])]]
         ^{:key k}
         [:<>
          [discipline-element k level-cursor]
          (let [available-powers
                (select-keys (k data/disciplines) (range 1 (inc @level-cursor)))
                power-options
                (reduce-kv (fn [acc k v] (conj acc [k, (:name v)]))
                           [["", "--"]]
                           (reduce merge (vals available-powers)))]
            (doall
             (for [i (range @level-cursor)]
               ^{:key i}
               [:<>
                [:div.pure-u-5-24 (humanize (str (name k) " power"))]
                [:div.pure-u-19-24
                 (select-input-with-cb
                  power-options #()
                  #_(swap! powers-cursor conj (.. % -target -value)))]])))]))]]))

(defn final-charsheet-page []
  [:p "Hey hai"])

(def pages
   [[intro-page]
    [clan-page]
    [attributes-page]
    [skills-page]
    [disciplines-page]
    [final-charsheet-page]])

(defn has-next-page? [page] (< @page (dec (count pages))))
(defn has-prev-page? [page] (> @page 0))
(defn next-page [page] (when (has-next-page? page) (swap! page inc)))
(defn prev-page [page] (when (has-prev-page? page) (swap! page dec)))

(defn page-id [id] (str "page" id))
(defn home-page [current-page]
  [:div
   (nth pages @current-page [:div "Page not found"])
   [:div.pure-button-group
    [:button.pure-button
     {:on-click #(prev-page current-page)
      :disabled (not (has-prev-page? current-page))}
     "Previous"]
    [:button.pure-button
     {:on-click #(next-page current-page)
      :disabled (not (has-next-page? current-page))}
     "Next"]]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
