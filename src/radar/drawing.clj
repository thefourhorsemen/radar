(ns radar.drawing
  (:require [quil.core :as q])
  (:gen-class))

(defn center []
  [(/ (q/width) 2)
   (/ (q/height) 2)])

(def white 255)
(def black 0)

(defn draw-circle [configuration]
  (let [[xcenter ycenter] (center)]
    (doseq [t (reverse (range 1 (inc (count configuration))))]
      (q/fill white)
      (q/stroke black)
      (q/ellipse xcenter ycenter (* 200 t) (* 200 t)))))

(defn draw-axis []
  (let [[xcenter ycenter] (center)]
    (q/line xcenter 0 xcenter (q/height))
    (q/line 0 ycenter (q/width) ycenter)))

(defn draw-category [color text [x y]]
  (let [horizontal-margin 10
        vertical-size 50
        text-width (q/text-width text)
        rx (if (= x 0) x (- x text-width (* 2 horizontal-margin)))
        ry (if (= y 0) y (- y vertical-size))]
    (q/stroke white)
    (q/fill color)
    (q/rect rx ry (+ (* 2 horizontal-margin) text-width) vertical-size)
    (q/fill white)
    (q/text text (+ horizontal-margin rx) (+ (- vertical-size 20) ry))))

(defn draw-categories [categories]
  (q/text-size 20)
  (let [red (q/color 255 0 0)
        green (q/color 0 255 0)
        blue (q/color 0 0 255)
        purple (q/color 50 0 50)
        colors [blue red green purple]
        positions [[0 0] [(q/width) 0] [(q/width) (q/height)] [0 (q/height)]]]
    (doall (map draw-category colors categories positions))))

(defn draw [categories configuration]
  (q/background white)
  (draw-circle configuration)
  (draw-axis)
  (draw-categories categories))

(defn draw-radar [categories configuration]
  ; run sketch
  (q/defsketch radar :size [900 900] :draw (partial draw categories configuration)))