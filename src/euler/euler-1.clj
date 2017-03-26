
;;; Project Euler problem #1
;;; Add all the natural numbers below one thousand that are multiples of 3 or 5

(def result 233168)

;;; Worm strategy: multiples of 3 + multiples of 5 - multiples of 15
(defn mul [n step] (apply + (range 0 n step)))
(defn euler1a [a b n] (+ (mul n a) (mul n b) (mul (- n) (- (* a b)))))
;;; (assert (= result (time (euler1a 3 5 1000))))

;;; Straight pick and check on any number 0..n (~1.5 times a)
(defn multiple? [a b] (zero? (mod a b)))
(defn euler1b [a b n] (apply + (for [x (range 0 n) :when (or (multiple? x a) (multiple? x b))] x)))
;;; (assert (= result (time (euler1b 3 5 1000))))

;;; generator/ lazy list (~ 3 times a)
(defn generator [a n] (apply + (take (/ n a) (iterate #(+ a %) 0))))
(defn euler1c [a b n] (+ (generator a n) (generator b n) (generator (- (* a b)) (- n))))
;;; (assert (= result (time (euler1c 3 5 1000))))

;;; Let's get fancy and check some multi threading worms...
;;; Same as euler1a except for the future wrapper
;;; On a 8 core sunw @ 2150 MHz, one needs to adjust from 1000 to 1000000 before the future version
;;; is faster than plain euler1a
(defn euler1d [a b n] (+ @(future (mul n a)) @(future (mul n b)) @(future (mul (- n) (- (* a b))))))
;;; (assert (= result (time (euler1d 3 5 1000))))

(doseq [f [euler1a euler1b euler1c euler1d]]
  (assert (= result (time (f 3 5 1000)))))

(println "Done")

;;; EOF)
