(ns xilop.components.projects
  (:require
   [xilop.state :as state]))

(defn aproject [{:keys [id name fields owners slug covers stats]}]
  (let [owner-name
        (get (first owners) :display_name)]

    [:div.card
     [:div.image
      [:img
       {:src
        (get covers :230)
        :alt name}]]
     [:div.content
      [:div.header [:a
                    {:href (str "/projects/" id)}
                    name]]
      [:div.meta [:span.date
                  (str "by " owner-name)]]
      [:div.description]]
     [:div.content.extra
      [:span.right.floated
       (first fields)]
      [:span [:i.icon.thumbs.up]
       (get stats :appreciations)]]]))

(defn projects []
  [:div.ui.link.cards
   (map aproject
        @state/projects)
   [:h1 "mee"]])
