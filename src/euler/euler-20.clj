; Euler #20

(defn factorial  [n] (reduce * (range 2 (inc n))))
(defn sum-digits [s] (reduce + (map #(Integer/parseInt (str %)) s)))
(defn euler-20   []  (sum-digits (str (factorial 100))))

(let [n (euler-20)]
     (println n))

; EOF
