(ns reag.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]))

;; -------------------------
;; State

(defonce charsheet (r/atom {}))

;; -------------------------
;; Views

(defn aligned-text-input [label]
   [:div.pure-control-group
    [:label {:for label} (clojure.string/capitalize label)]
    [:input {:name label :id label
             :on-blur
             #(swap! charsheet assoc (keyword label) (.. % -target -value))}]])

(defn home-page []
  [:div.pure-form.pure-form-aligned
   [:fieldset
    [aligned-text-input "name"]
    [aligned-text-input "player"]
    [aligned-text-input "chronicle"]
    [aligned-text-input "concept"]
    [aligned-text-input "ambition"]]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
