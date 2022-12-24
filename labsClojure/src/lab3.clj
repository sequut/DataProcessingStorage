(ns lab3)
(require '[clojure.test :as test])

(defn internalFilter [function block]
  (future (doall (filter function block))))

(defn parallelFilterInternal [function seq]
  (let [size 3]
    (->> (partition-all size seq)
         (map #(internalFilter function %))
         (doall)
         (map deref)
         (concat))))

(defn parallelFilter [function seq]
  (flatten (apply concat (map #(parallelFilterInternal function %)
                              (partition-all 200 seq)))))

(defn check [a]
  (Thread/sleep 10)
  even? a)


(defn bigSeq [n]
  (take n (iterate inc 1)))

(defn takeTimeParallelFilterInString [func seq]
  (nth (clojure.string/split (with-out-str (time (parallelFilter func seq))) #" ") 2))

(defn takeTimeParallelFloat [func seq]
  (Float/parseFloat (takeTimeParallelFilterInString func seq)))

(defn takeTimeFilterInString [func seq]
  (nth (clojure.string/split (with-out-str (time (filter func seq))) #" ") 2))

(defn takeTimeFloat [func seq]
  (Float/parseFloat (takeTimeParallelFilterInString func seq)))

;(time (println (parallelFilter check (bigSeq 100))))
;(time (println (filter check (bigSeq 100))))

;(println (takeTimeParallelFloat check (bigSeq 100)))
;(println (takeTimeFloat check (bigSeq 100)))


(test/deftest lab3Test
  (test/testing "tests:"
    (test/is (= (parallelFilter even? (bigSeq 10)) (list 2 4 6 8 10)))
    (test/is (> (takeTimeFloat check (bigSeq 1000)) (* (takeTimeParallelFloat check (bigSeq 1000)) 1)))))