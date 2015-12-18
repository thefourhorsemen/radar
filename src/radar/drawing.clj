(ns radar.drawing
  (:require [quil.core :as q])
  (:gen-class))

(defn- center []
  [(/ (q/width) 2)
   (/ (q/height) 2)])

(def ^:private radius 200)

(def ^:private white 255)
(def ^:private black 0)
(defn- red [] (q/color 200 0 0))
(defn- green [] (q/color 0 100 0))
(defn- blue [] (q/color 0 0 200))
(defn- purple [] (q/color 75 0 75))

(defn- op-direction [direction]
  (if (or (= direction :west) (= direction :north)) - +))

(defn- draw-circle [levels]
  (let [[xcenter ycenter] (center)]
    (doseq [t (reverse (range 1 (inc (count levels))))]
      (q/fill white)
      (q/stroke black)
      (q/ellipse xcenter ycenter (* radius t) (* radius t)))))

(defn- draw-axis []
  (let [[xcenter ycenter] (center)]
    (q/line xcenter 0 xcenter (q/height))
    (q/line 0 ycenter (q/width) ycenter)))

(defn- draw-levels [levels]
  (let [[xcenter ycenter] (center)]
    (q/fill black)
    (q/text-align :center :center)
    (doall (map q/text levels (repeat xcenter)
             (iterate (fn [y] (- y (/ radius 1.85))) (- ycenter 30))))))

(defn- draw-category [color text [x y]]
  (let [horizontal-margin 10
        vertical-size 40
        text-width (q/text-width text)
        rx (if (= x 0) x (- x text-width (* 2 horizontal-margin)))
        ry (if (= y 0) y (- y vertical-size))]
    (q/stroke white)
    (q/fill color)
    (q/rect rx ry (+ (* 2 horizontal-margin) text-width) vertical-size 7)
    (q/fill white)
    (q/text-align :left)
    (q/text text (+ horizontal-margin rx) (+ (- vertical-size 15) ry))))

(defn- draw-categories [categories]
  (q/text-size 20)
  (let [colors [(blue) (red) (green) (purple)]
        positions [[0 0] [(q/width) 0] [(q/width) (q/height)] [0 (q/height)]]]
    (doall (map draw-category colors categories positions))))

(defn- draw-target [color text [lat-direction long-direction] offset step]
  (let [[xcenter ycenter] (center)
        opx (op-direction long-direction)
        opy (op-direction lat-direction)]
    (q/fill color)
    (q/text-align :center :center)
    (doall (map q/text text
             (iterate (fn [x] (opx x step)) (opx xcenter offset))
             (iterate (fn [y] (opy y step)) (opy ycenter offset))))))

(defn- draw-targets [targets]
  (let [colors [(blue) (red) (green) (purple)]
        directions [[:north :west] [:north :east] [:south :east] [:south :west]]]
    (doall (map draw-target colors targets directions (repeat (/ radius 3.5)) (repeat (/ radius 3))))))

(defn- draw [categories targets output-file]
  (let [levels ["Adopt" "Trial" "Assess" "Hold"]]
    (q/text-font (q/create-font "Bold Candara" 20))
    (q/background white)
    (draw-circle levels)
    (draw-axis)
    (draw-levels levels)
    (draw-categories categories)
    (draw-targets targets)
    (q/save output-file)
    (q/no-loop)))

(defn draw-radar [categories targets output-file]
  ; run sketch
  (q/defsketch radar :size [900 900] :draw (partial draw categories targets output-file)))