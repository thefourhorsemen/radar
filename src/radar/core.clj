(ns radar.core
  (:require [radar.config :refer :all]
            [radar.drawing :refer :all])
  (:gen-class))

(defn -main [& args]
  (let [filename (first args)
        configuration (read-configuration filename)]
    (draw-radar (targets configuration) configuration)))
