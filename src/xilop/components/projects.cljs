(ns xilop.components.projects
  (:require
   [xilop.state :as state]
   [secretary.core :as secretary]
   [accountant.core :as accountant]))

(defn aproject [{:keys [id name fields owners  covers stats]}]
  (let [owner-name (get (first owners) :display_name)]
    [:div.card {:key id}
     [:div.image
      [:img
       {:src
        (get covers :230)
        :alt name}]]
     [:div.content
      [:div.header [:a
                    ; {:on-click #(accountant/navigate! (str "/projects/" id))}
                    ; {:href (str "/#projects/" id)}
                    (secretary/dispatch! (str "/projects/" id))
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
        @state/projects)])
