(ns radar.config
  (:require [clj-json.core :as json]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(defn- read-json-configuration [filename]
  (json/parse-string (slurp filename)))

(defn- create-entry [line]
  (let [line-components (str/split line #":")
        name (str/trim (first line-components))
        categories (mapv str/trim (str/split (last line-components) #","))]
    (hash-map "name" name 
              "categories" categories)))

(defn- read-txt-configuration [filename]
  (let [content (slurp filename)
        lines (str/split content #"\n")]
    (mapv create-entry lines)))

(def ^:private readers (hash-map "json" (fn [filename] (read-json-configuration filename)) 
                                 "txt"  (fn [filename] (read-txt-configuration  filename))))

(defn read-configuration [filename]
  (let [file-comps (str/split filename #"\.")
        ext (last file-comps)
        reader (readers ext)]
    (if (not reader) (throw (IllegalArgumentException. (str "configuration with extension " ext " is not supported." ))))
    (reader filename)))

(defn categories [configuration]
  (map #(% "name") configuration))

(defn targets [configuration]
  (map #(% "categories") configuration))

