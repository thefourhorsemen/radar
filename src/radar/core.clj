(ns radar.core
  (:require [radar.config :refer :all]
            [radar.drawing :refer :all]
            [clojure.string :as str])
  (:gen-class))

(defn -main [& args]
  (let [config-file (first args)
        output-file (str (first (str/split config-file #"\.")) ".png")
        configuration (read-configuration config-file)]
    (draw-radar (categories configuration) (targets configuration) output-file)))