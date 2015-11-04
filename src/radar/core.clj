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

(defn draw-circle [configuration]
  ; make background white
  ;  (q/background 255)
  (let [[xcenter ycenter] (center)]
    (doseq [t (reverse (range 1 (inc (count configuration))))]
      (q/ellipse xcenter ycenter (* 200 t) (* 200 t)))))

(defn draw-axis []
  (let [[xcenter ycenter] (center)]
    (q/line xcenter 0 xcenter (q/height))
    (q/line 0 ycenter (q/width) ycenter)))

(defn draw-targets [targets]
  ;  (q/fill 0)
  (q/text-size 20)
  (q/text (nth targets 0) 0 0)
  (q/text (nth targets 1) (q/width) 0)
  (q/text (nth targets 2) (q/width) (q/height))
  (q/text (nth targets 3) 0 (q/height)))

(defn draw-configuration [configuration]
  (draw-circle configuration)
  (draw-axis)
  (draw-targets (targets configuration)))

(defn -main [& args]
  (let [filename (first args)
        configuration (read-configuration filename)]
    ; run sketch
    (q/defsketch radar :size [900 900] :draw (partial draw-configuration configuration))))
