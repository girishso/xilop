(ns xilop.state
  (:require [reagent.core :as r]))

(def orders (r/atom {}))

(def projects (r/atom {}))

(def user (r/atom nil))
