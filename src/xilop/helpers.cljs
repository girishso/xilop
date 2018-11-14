(ns xilop.helpers)

(defn format-price [cents] (str " $" (/ cents 100)))
