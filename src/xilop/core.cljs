(ns xilop.core
  ; (:require-macros [secretary.core :refer [defroute]])

  (:require [reagent.core :as r]
            [xilop.components.header :refer [header]]
            [xilop.api :as api]
            [xilop.routes :as routes]
            [xilop.layout :as layout]
            [accountant.core :as accountant]
            [secretary.core :as secretary]))

(defn app
  []
  [:div.container
   [header]])

(defn mount-root []
  (r/render
    [layout/layout]
    (.getElementById js/document "app")))

(defn ^:export main []
  ; (routes/app-routes)
  (api/fetch-projects)
  (routes/hook-browser-navigation!)
  (accountant/configure-navigation!
   {:nav-handler
    (fn [path]
      (secretary/dispatch! path))
    :path-exists?
    (fn [path]
      (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
