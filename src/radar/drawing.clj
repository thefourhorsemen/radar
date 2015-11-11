(ns radar.drawing
  (:require [quil.core :as q])
  (:gen-class))

(defn center []
  [(/ (q/width) 2)
   (/ (q/height) 2)])

(def radius 200)

(def white 255)
(def black 0)

(defn red [] (q/color 255 0 0))
(defn green [] (q/color 0 255 0))
(defn blue [] (q/color 0 0 255))
(defn purple [] (q/color 50 0 50))

(defn draw-circle [configuration]
  (let [[xcenter ycenter] (center)]
    (doseq [t (reverse (range 1 (inc (count configuration))))]
      (q/fill white)
      (q/stroke black)
      (q/ellipse xcenter ycenter (* radius t) (* radius t)))))

(defn draw-axis []
  (let [[xcenter ycenter] (center)]
    (q/line xcenter 0 xcenter (q/height))
    (q/line 0 ycenter (q/width) ycenter)))

(defn draw-levels [levels]
  (let [[xcenter ycenter] (center)]
    (q/fill black)
    (q/text-align :center :center)
    (doall (map q/text levels (repeat xcenter)
             (iterate (fn [y] (- y (/ radius 1.85))) (- ycenter 30))))))

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
    (q/text-align :left)
    (q/text text (+ horizontal-margin rx) (+ (- vertical-size 20) ry))))

(defn draw-categories [categories]
  (q/text-size 20)
  (let [colors [(blue) (red) (green) (purple)]
        positions [[0 0] [(q/width) 0] [(q/width) (q/height)] [0 (q/height)]]]
    (doall (map draw-category colors categories positions))))

(defn draw-target [color text [x y] [opx opy]]
  (let [[xcenter ycenter] (center)]
    (q/fill color)
    (q/text-align :center :center)
    (doall (map q/text text
             (iterate (fn [x] (opx x (/ radius 3))) (+ xcenter x))
             (iterate (fn [y] (opy y (/ radius 3))) (- ycenter y))))))

(defn draw-targets [targets]
  (let [nradius (/ radius 3.5)
        colors [(blue) (red) (green) (purple)]
        positions [[(- 0 nradius) nradius] [nradius nradius] [nradius (- 0 nradius)] [(- 0 nradius) (- 0 nradius)]]
        operators [[- -] [+ -] [+ +] [- +]]]
    (doall (map draw-target colors targets positions operators))))

(defn draw [categories targets configuration]
  (q/background white)
  (draw-circle configuration)
  (draw-axis)
  (draw-levels ["Adopt" "Trial" "Assess" "Hold"])
  (draw-categories categories)
  (draw-targets targets))

(defn draw-radar [categories targets configuration]
  ; run sketch
  (q/defsketch radar :size [900 900] :draw (partial draw categories targets configuration)))