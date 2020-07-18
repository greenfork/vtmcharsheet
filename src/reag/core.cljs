(ns reag.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]
      [clojure.string :as str]))

;; -------------------------
;; State

(defonce charsheet (r/atom {}))
(defonce page (r/atom 0))

;; -------------------------
;; Views

(defn aligned-text-input [label]
   [:div.pure-control-group
    [:label {:for label} (str/capitalize label)]
    [:input {:name label :id label :value ((keyword label) @charsheet)
             :on-change
             #(swap! charsheet assoc (keyword label) (.. % -target -value))}]])

(defn page-id [id] (str "page" id))

(def pages
  [
   ;; First page
   [:div.pure-form.pure-form-aligned [:fieldset]
    [aligned-text-input "name"]
    [aligned-text-input "player"]
    [aligned-text-input "chronicle"]
    [aligned-text-input "concept"]
    [aligned-text-input "ambition"]]
   ;; Second page
   [:div "second page"]
   ;; Third page
   [:div "third page"]])

(defn has-next-page? [] (< @page (dec (count pages))))
(defn has-prev-page? [] (> @page 0))
(defn next-page [] (when (has-next-page?) (swap! page inc)))
(defn prev-page [] (when (has-prev-page?) (swap! page dec)))

(defn home-page []
  [:div
   (nth pages @page [:div "Page not found"])
   [:button.pure-button
    {:on-click prev-page :disabled (not (has-prev-page?))}
    "Previous"]
   [:button.pure-button
    {:on-click next-page :disabled (not (has-next-page?))}
    "Next"]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
