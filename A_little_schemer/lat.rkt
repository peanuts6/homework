#lang racket

(define atom?
  (lambda (x)
    (and
     (not (null? x))
     (not (pair? x))
     )
  )
)

(define lat?
  (lambda(l)
    (cond
        ((null? l) 1)
        ((atom? (car l)) (lat? (cdr l)))
        (else 0))))


(lat?'())
(lat?'(1 2 3))
(lat?'(1 (2 3) 4))