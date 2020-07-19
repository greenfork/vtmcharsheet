(ns vtmcharsheet.core
    (:require
     [reagent.core :as r]
     [reagent.dom :as d]
     [clojure.string :as str]
     [vtmcharsheet.data :as data]))

;; -------------------------
;; State

(defonce charsheet
  ;; Default values are set here.
  (r/atom {:clan "brujah"
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
;; Views

(defn aligned-text-input [label placeholder]
   [:div.pure-control-group
    [:label {:for label} (str/capitalize label)]
    [:input {:name label :id label :value ((keyword label) @charsheet)
             :placeholder placeholder
             :on-change
             #(swap! charsheet assoc (keyword label) (.. % -target -value))}]])

(defn aligned-select-map [label coll access-sequence]
  [:div.pure-control-group
   [:label {:for label} (str/capitalize label)]
   [:select {:id label :value ((keyword label) @charsheet)
             :on-change
             #(swap! charsheet assoc (keyword label) (.. % -target -value))}
    (for [[k v] coll]
      ^{:key k} [:option {:value k} (get-in v access-sequence)])]])

(defn aligned-select-array [label coll]
  [:div.pure-control-group
   [:label {:for label} (str/capitalize label)]
   [:select {:id label
             :on-change
             #(swap! charsheet assoc (keyword label) (.. % -target -value))}
    (for [v coll]
      ^{:key v} [:option {:value v} v])]])

(defn clan-description []
  (let [clan-info ((keyword (:clan @charsheet)) data/clans)]
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
     [:div (:bane clan-info)]]))

(defn clamp [value min-value max-value] (max (min value max-value) min-value))

(defn circle-input [cursor min-value max-value]
  [:div.circle-input
   (doall
     (for [v (range 1 (inc max-value))]
       ^{:key v} [:span.circle
                  {:class [(when (<= v @cursor) :active)]}]))
   [:span.pure-button-group.circle-control
    [:button.pure-button
     {:on-click #(swap! cursor (fn [v] (clamp (dec v) min-value max-value)))}
     "-"]
    [:button.pure-button
     {:on-click #(swap! cursor (fn [v] (clamp (inc v) min-value max-value)))}
     "+"]]])

(defn attribute-element [k]
  ^{:key k}
  [:<>
   [:div.pure-u-5-24 (str/capitalize (name k))]
   [:div.pure-u-19-24
    [circle-input (r/cursor charsheet [:attributes k]) 1 5]]])

(defn intro-page []
  [:div.pure-form.pure-form-aligned
   [:fieldset
    [aligned-text-input "name" "Character's name"]
    [aligned-text-input "player" "Your real name"]
    [aligned-text-input "chronicle" "The title of a chronicle"]
    [aligned-text-input "concept" "What is your character about?"]
    [aligned-text-input "ambition" "What do you want the most?"]]])

(defn clan-page []
  [:div.pure-form.pure-form-aligned
   [:fieldset
    [aligned-text-input "sire" "Name of your sire"]
    [aligned-select-map "clan" data/clans [:name]]
    [aligned-select-array "generation" (range 1 20)]
    [:div [clan-description]]]])

(defn attributes-page []
  [:div
   [:h2 "Attributes"]
   [:p "Take one attribute at 4; three attributes at 3; four attributes at 2; one attribute at 1."]
   [:div.pure-g
    (for [[k _] (:attributes @charsheet)]
      (attribute-element k))]])

(defn skills-page []
  (r/with-let [skill-distribution (r/cursor charsheet [:skill-distribution])]
    [:div
     [:h2 "Skills"]
     [:p
      "Skill distribution: "
      [:span.pure-button-group
       [:button.pure-button
        {:class [(when (= @skill-distribution :jack-of-all-trades) :pure-button-active)]
         :on-click #(reset! skill-distribution :jack-of-all-trades)}
        "Jack-of-all-trades"]
       [:button.pure-button
        {:class [(when (= @skill-distribution :balanced) :pure-button-active)]
         :on-click #(reset! skill-distribution :balanced)}
        "Balanced"]
       [:button.pure-button
        {:class [(when (= @skill-distribution :specialist) :pure-button-active)]
         :on-click #(reset! skill-distribution :specialist)}
        "Specialist"]]]
     [:p (:description (@skill-distribution data/skill-distributions))]]))


(def pages
  (vector
   [intro-page]
   [clan-page]
   [attributes-page]
   [skills-page]))

(defn has-next-page? [page] (< @page (dec (count pages))))
(defn has-prev-page? [page] (> @page 0))
(defn next-page [page] (when (has-next-page? page) (swap! page inc)))
(defn prev-page [page] (when (has-prev-page? page) (swap! page dec)))

(defn page-id [id] (str "page" id))
(defn home-page []
  (r/with-let [current-page page]
    (fn []
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
         "Next"]]])))

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))