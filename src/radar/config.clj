(ns radar.config
  (:require [clj-json.core :as json]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(defn- create-entry [line]
  (let [line-components (str/split line #":")
        name (str/trim (first line-components))
        categories (mapv str/trim (str/split (last line-components) #","))]
    (hash-map "name" name
      "categories" categories)))

(defmulti read-configuration (fn [filename] (let [file-comps (str/split filename #"\.")
                                                  ext (str/lower-case (last file-comps))] (keyword ext))))

(defmethod read-configuration :json [filename]
  (json/parse-string (slurp filename)))

(defmethod read-configuration :txt [filename]
  (let [content (slurp filename)
        lines (str/split content #"\n")]
    (mapv create-entry lines)))

(defmethod read-configuration :default [filename]
  (throw (IllegalArgumentException. (str "Unable to decode configuration file named " filename))))

(defn categories [configuration]
  (map #(% "name") configuration))

(defn targets [configuration]
  (map #(% "categories") configuration))

