(ns xilop.layout
  (:require [xilop.state :as state]
            [xilop.components.projects :refer [projects]]
            [xilop.components.project :refer [project]]))

(defn not-found []
  [:h1 "Not found"])

; (defmulti current-page #(@state/app-state)
;   (defmethod current-page [:home] []
;     [:h1 "Home"])
;   (defmethod current-page [:project id] []
;     [:h1 "project"]))
;   ; (defmethod current-page [:default] []
;   ;   [:h1 "default"]))

(defn current-page []
  ; (let [[page params] (get @state/app-state :page)])
  (let [[page params] @state/app-state]
    (cond
      (= page :home) [projects]
      (= page :project) [project (:id params)]
      :else not-found)))

(defn layout []
  [:main
   [:aside
    [:h1
     [:a {:href "/"}
      "Homex"]]
    [:h3
     "app-state"
     (str ": " @state/app-state)]]
   [:content
    [:h1 "content"]
    [current-page]]])
