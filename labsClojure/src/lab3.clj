(ns lab3)
(require '[clojure.test :as test])

(defn internalFilter [function block]
  (future (doall (filter function block))))

(defn parallelFilter [func seq]
  (let [size (int (Math/ceil (Math/sqrt (count seq))))]
    (->> (partition-all size seq)
         (map #(internalFilter func %))
         (doall)
         (map deref)
         (reduce concat)
         )))

(defn bigSeq [n]
  (take n (iterate inc 1)))

(defn takeTimeParallelFilterInString [func seq]
  (nth (clojure.string/split (with-out-str (time (parallelFilter func seq))) #" ") 2))

(defn takeTimeFloat [func seq]
  (Float/parseFloat (takeTimeParallelFilterInString func seq)))

(println (takeTimeFloat even? (bigSeq 100)))
(println (takeTimeFloat even? (bigSeq 100)))
(println)

(test/deftest lab3Test
  (test/testing "tests:"
    (test/is (= (parallelFilter even? (bigSeq 10)) (list 2 4 6 8 10)))
    (test/is (> (takeTimeFloat even? (bigSeq 10000)) (* (takeTimeFloat even? (bigSeq 10000)) 1)))
    ))