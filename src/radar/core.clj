(ns radar.core
  (:require [clj-json.core :as json]
            [clojure.java.io :as io]
            [quil.core :as q])
  (:gen-class))

(defn read-configuration [filename]
  (json/parse-string (slurp filename)))

(defn draw []
  ; make background white
  (q/background 255)
  (let [xcenter (/ (q/width) 2)
        ycenter (/ (q/height) 2)]
    (doseq [t (reverse (range 1 (inc 4)))]
      (q/ellipse xcenter ycenter (* 200 t) (* 200 t)))
    (q/line xcenter 0 xcenter (q/height))
    (q/line 0 ycenter (q/width) ycenter)))
0
(defn -main [& args]
  (let [filename (first args)
        configuration (read-configuration filename)]

    ; run sketch
    (q/defsketch radar :size [900 900] :draw draw)))
