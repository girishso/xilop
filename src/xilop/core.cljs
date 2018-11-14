(ns xilop.core
  (:require [reagent.core :as r]
            [xilop.components.header :refer [header]]
            [xilop.components.projects :refer [projects]]
            [xilop.api :as api]))

(defn app
  []
  [:div.container
   [header]
   [projects]])

(defn ^:export main
  []
  (api/fetch-projects)
  (r/render
    [app]
    (.getElementById js/document "app")))
