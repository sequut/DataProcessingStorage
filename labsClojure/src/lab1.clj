(ns lab1)
(defn filterLast [word alphabet]
  (apply list (filter #(not= (last word) %) alphabet)))

(defn foo [word alphabet]
  (apply list (map #(str word %) (filterLast (clojure.string/split word #"") alphabet))))

(defn bar [words alphabet]
  (apply concat (map #(foo % alphabet) words)))

(defn c1 [alphabet n]
  (reduce bar (repeat n alphabet)))

(println (c1 (list "a" "b" "c") 3))