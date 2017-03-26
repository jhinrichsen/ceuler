; Collatz sequence

(ns euler)

(defn next-collatz
  "Return next item in collatz sequence"
  [n]
  (if (even? n)
    (/ n 2)
    (inc (* 3 n))))

(defn collatz-seq
  "Return a collatz sequence for a given n"
  ([n]
    ; Using a vector because conj will append to tail instead of head
    (collatz-seq n [n]))

  ([n l]
    (if (= 1 n)
      l
      (let [m (next-collatz n)]
        (recur m (conj l m))))))

; Takes ~13 seconds on a MacBook Air
(defn euler-14
  ([]
   (euler-14 1000000))

  ([n]
    (time (println (reduce max (map count (map collatz-seq (range 1 n))))))))


; OOM
; (time (println (reduce max (map count (map (memoize collatz-seq) (range 1 1000000))))))
