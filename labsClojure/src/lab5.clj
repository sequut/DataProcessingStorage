(ns lab5)

(def philosophersNumber 4)
(def forksNumber philosophersNumber)
(def thinkingTime 20)
(def diningTime 25)
(def periods 6)

(def transactions_starts (atom 0))
(def transactions_finishes (atom 0))

(def forks (doall (map (fn [ _ ] (ref 0)) (range forksNumber))))

(defn philosopher [left_fork, right_fork, i]
  (dorun periods
         (repeatedly
           (fn []
             (do
               (println (format "%d thinking" i))
               (Thread/sleep thinkingTime)
               (println (format "%d trying to eat" i))
               (dosync
                 (println (format "%d started again transaction" i))
                 (swap! transactions_starts inc)
                 (alter left_fork inc)
                 (alter right_fork inc)
                 (Thread/sleep diningTime))

               (swap! transactions_finishes inc)
               (println (format "%d finished transaction" i)))))))

(def threads (doall (map (fn [i]
        (new Thread
             (fn []
               (philosopher (nth forks i) (nth forks (mod (inc i) forksNumber)) i)))) (range philosophersNumber))))


(defn eating []
  (do
    (doall (map (fn [thread] (.start thread)) threads))
    (doall (map (fn [thread] (.join thread))  threads))
    ))

(time (eating))
(println "transactions started" (deref transactions_starts))
(println "transactions finished" (deref transactions_finishes))
(println "transactions started again" (- (deref transactions_starts) (deref transactions_finishes)))