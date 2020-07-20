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
           :attributes (array-map
                        :strength 1
                        :dexterity 1
                        :stamina 1
                        :charisma 1
                        :manipulation 1
                        :composure 1
                        :intelligence 1
                        :wits 1
                        :resolve 1)
           :skill-distribution :jack-of-all-trades
           :skills (array-map
                    ;; 1
                    :athletics {}
                    :brawl {}
                    :craft {}
                    :drive {}
                    :firearms {}
                    :melee {}
                    :larceny {}
                    :stealth {}
                    :survival {}
                    ;; 2
                    :animal-ken {}
                    :etiquette {}
                    :insight {}
                    :intimidation {}
                    :leadership {}
                    :performance {}
                    :persuasion {}
                    :streetwise {}
                    :subterfuge {}
                    ;; 3
                    :academics {}
                    :awareness {}
                    :finance {}
                    :investigation {}
                    :medicine {}
                    :occult {}
                    :politics {}
                    :science {}
                    :technology {})}))
(defonce page (r/atom 0))

;; -------------------------
;; Utilities

(defn humanize [x] (str/capitalize (str/replace (name x) "-" " ")))

(defn clamp [value min-value max-value] (max (min value max-value) min-value))

(defn count-elems [pred coll]
  (reduce (fn [acc x] (if (pred x) (inc acc) acc)) 0 coll))

(defn pluralize
  "Poor man's pluralization function."
  ([x word]
   (if (#{\a \o \u \i} (last word))
     (pluralize x word (str word "es"))
     (pluralize x word (str word "s"))))
  ([x word plural-word]
   (if (== 1 (mod x 10)) word plural-word)))

(def numbers {0 "zero"
              1 "one"
              2 "two"
              3 "three"
              4 "four"
              5 "five"})

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
     (humanize (name k))]]
   [:div.pure-u-19-24 [circle-input cursor 1 5 (k data/attributes)]]])


(defn distribution-line
  "Produces line like the following:
  Take three `unit-name` at 3: (2/3)"
  [value attrs validations unit-name]
  (let [valid (validations value)
        number-of-elems (count-elems #{value} attrs)]
    (when (or (not (zero? valid)) (pos? number-of-elems))
      [:<>
       [:span "Take " (numbers valid) " "
        (pluralize valid unit-name) " at " value ": "
        [:span
         {:class [(if (== valid number-of-elems) "correct-color" "wrong-color")]}
         "(" number-of-elems "/" valid ")"]
        (when (> number-of-elems valid) [:span.text-secondary " too much"])
        (when (< number-of-elems valid) [:span.text-secondary " not enough"])]
       [:br]])))

(defn distribution-text
  [attrs validations unit-name]
  (for [x (reverse (range 1 6))]
    [distribution-line x attrs validations unit-name]))

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
     (humanize (name k))]]
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
   [:div (str/join ", " (:disciplines clan-info))]
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
   [:p (distribution-text
        (vals (:attributes @charsheet))
        {5 0, 4 1, 3 3, 2 4, 1 1}
        "attribute")]
   [:p "Hover over any element to get a hint."]
   [:div.pure-g
    (for [[k _] (:attributes @charsheet)]
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
      [:p (:description (@skill-distribution data/skill-distributions))]]
     [:p "Hover over any element to get a hint."]
     [:div
      (for [[k _] (:skills @charsheet)]
        ^{:key k} [skill-element k (r/cursor charsheet [:skills k])])]]))

(def pages
   [[intro-page]
    [clan-page]
    [attributes-page]
    [skills-page]])

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
