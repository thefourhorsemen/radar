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

(defn draw-target [color text x y]
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

(defn draw-targets [targets]
  (q/text-size 20)
  (let [red (q/color 255 0 0)
        green (q/color 0 255 0)
        blue (q/color 0 0 255)
        purple (q/color 50 0 50)]
    (draw-target blue (nth targets 0) 0 0)
    (draw-target red (nth targets 1) (q/width) 0)
    (draw-target green (nth targets 2) (q/width) (q/height))
    (draw-target purple (nth targets 3) 0 (q/height))))

(defn draw [targets configuration]
  (q/background white)
  (draw-circle configuration)
  (draw-axis)
  (draw-targets targets))

(defn draw-radar [targets configuration]
  ; run sketch
  (q/defsketch radar :size [900 900] :draw (partial draw targets configuration)))