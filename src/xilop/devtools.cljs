(ns xilop.devtools
  (:require [devtools.core :as devtools]))

(devtools/install!)

(defn x [] (.log js/console (range 200)))
