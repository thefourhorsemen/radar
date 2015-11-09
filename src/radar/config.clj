(ns radar.config
  (:require [clj-json.core :as json]
            [clojure.java.io :as io])
  (:gen-class))

(defn read-configuration [filename]
  (json/parse-string (slurp filename)))

(defn categories [configuration]
  (map #(% "name") configuration))

(defn targets [configuration]
  (map #(% "categories") configuration))

