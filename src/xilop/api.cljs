(ns xilop.api
  (:require [ajax.core :refer [GET]]
            [xilop.state :as state]))

(defn index-by
  [key coll]
  "Transfomr a coll to a map with a given key as a lookup value"
  (->> coll
       (map (juxt key identity))
       (into {})))

(defn handler [response]
  (reset! state/projects (:projects response)))

(defn error-handler [{:keys [status status-text]}]
  (.log js/console (str "something bad happened: " status " " status-text)))

(defn fetch-projects
  []
  (GET "http://cuberoot.in:8080/http://www.behance.net/v2/projects?client_id=zAfaQfvw7LHUvnj4IRfolHMdh07R2Oll"
       {:handler handler
        :error-handler error-handler
        :response-format :json
        :keywords? true}))
