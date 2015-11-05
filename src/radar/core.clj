(ns radar.core
  (:require [clj-json.core :as json]
            [clojure.java.io :as io]
            [quil.core :as q])
  (:gen-class))

(defn read-configuration [filename]
  (json/parse-string (slurp filename)))

(defn targets [configuration]
  (map #(% "name") configuration))

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
  (q/stroke white)
  (q/fill color)
  (q/rect x y 200 50)
  (q/fill white)
  (q/text text (+ 10 x) (+ 25 y)))

(defn draw-targets [targets]
  (q/text-size 20)
  (let [red (q/color 255 0 0)
        green (q/color 0 255 0)
        blue (q/color 0 0 255)
        purple (q/color 50 0 50)]
    (draw-target blue (nth targets 0) 0 0)
    (draw-target red (nth targets 1) (- (q/width) 200) 0)
    (draw-target green (nth targets 2) (- (q/width) 200) (- (q/height) 50))
    (draw-target purple (nth targets 3) 0 (- (q/height) 50))))

(defn draw-configuration [configuration]
  (q/background white)
  (draw-circle configuration)
  (draw-axis)
  (draw-targets (targets configuration)))

(defn -main [& args]
  (let [filename (first args)
        configuration (read-configuration filename)]
    ; run sketch
    (q/defsketch radar :size [900 900] :draw (partial draw-configuration configuration))))
