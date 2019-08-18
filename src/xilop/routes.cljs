(ns xilop.routes
  (:import goog.history.Html5History)
  (:require     [goog.events :as events]
                [goog.history.EventType :as HistoryEventType]
                [secretary.core :as secretary  :refer-macros [defroute]]
                ; [reagent.session :as session]
                [xilop.state :as state]))
                ; [xilop.components.projects :refer [projects]]
                ; [xilop.components.project :refer [project]]))

(defn get-token []
  (if (Html5History.isSupported)
    (str js/window.location.pathname js/window.location.search)
    (if (= js/window.location.pathname "/")
      (.substring js/window.location.hash 1)
      (str js/window.location.pathname js/window.location.search))))

(defn handle-url-change [e]
  ;; log the event object to console for inspection
  (js/console.log e)
  ;; and let's see the token
  (js/console.log (str "Navigating: " (get-token)))
  ;; we are checking if this event is due to user action,
  ;; such as click a link, a back button, etc.
  ;; as opposed to programmatically setting the URL with the API
  (when-not (.-isNavigation e)
    ;; in this case, we're setting it
    (js/console.log "Token set programmatically")
    ;; let's scroll to the top to simulate a navigation
    (js/window.scrollTo 0 0))
  ;; dispatch on the token
  (secretary/dispatch! (get-token)))

(defn hook-browser-navigation! []
  (doto (goog.history.Html5History.)
    (events/listen HistoryEventType/NAVIGATE
                   #(handle-url-change %))
    (.setEnabled true)))

; (defn app-routes []
(secretary/set-config! :prefix "#")

(defroute home-route "/" []
          (reset! state/app-state   [:home]))
          ; (session/put! :current-page [:home]))

(defroute project-route "projects/:id" [id]
          (js/console.log (str "User: " id)))
          ; (reset! state/app-state   [:project, {:id id}]))
          ; (session/put! :current-page [:project, {:id id}]))


; (defn current-page [] [:div "xx"])
