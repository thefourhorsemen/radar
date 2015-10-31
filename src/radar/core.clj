(ns radar.core
  (:require [clj-json.core :as json]
            [clojure.java.io :as io])
  (:gen-class))

(defn read-configuration [filename]
  (json/parse-string (slurp filename)))

(defn -main [& args]
  (let [filename (first args)]
    (println (read-configuration filename))))
