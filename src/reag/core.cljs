(ns reag.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]))

;; -------------------------
;; Views


(defn lister [items]
  [:ul
   (for [item items]
     ^{:key item} [:li "Item " item])])

(defn lister-user []
  [:div
   "Here is a list:"
   [lister (range 3)]])

(defn text-input
  ([name] (text-input name name))
  ([name label]
   [:div
    [:label {:for name} (clojure.string/capitalize label)]
    [:input {:name name :id name}]]))

(defn home-page []
  [text-input "name"])

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
