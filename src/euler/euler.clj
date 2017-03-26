;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;
; Project euler problems (http://projecteuler.net)
;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;
; Euler #1
; Add all the natural numbers below one thousand that are multiples of 3 or 5.
;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def result-1 33165)

; sum = sum + n in imperative languages translate roughly to tail recursive
; calls in functional languages
(defn euler1-1
  ([n] (euler1-1 n 0))
  ([n sum] (if (= 0 n)
             sum
             (if (and (= 0 (mod n 3)) (= 0 (mod n 5)))
               (recur (dec n) (+ sum n))
               (recur (dec n) sum)))))
(time (assert (= result-1 (euler1-1 1000))))

; worms: add all 3s and all 5s, and subtract double counted 3s*5s
(defn euler1-2
  [n] (- (+ (range 1 n 3) (range 1 n 5)) (range 1 n 15)))
(time (assert (= result-1 (euler1-1 1000))))

; Sum of 1..1000 filtered by 3s and 5s
(defn euler1-3
  [n] (apply +
        (filter #(= 0 (mod % 3))
          (filter #(= 0 (mod % 5))
            (range 1 n)))))
(time (assert (= result-1 (euler1-3 1000))))

; best implementation
; Could use macro here
(defn euler1 [n] (euler1-2 n))
(time (assert (= result-1 (euler1 1000))))

; EOF
