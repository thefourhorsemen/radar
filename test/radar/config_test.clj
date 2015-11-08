(ns radar.config-test
  (:require [clojure.test :refer :all]
            [radar.config :refer :all]))

(def sample-configuration
  [{"name" "Robots"
    "categories" {"adopt" "Ar Drone"
                  "trial" "Phamtom X Hexapod"
                  "assess" "Myo Armband"
                  "hold" "Roomba"}
    }
   {"name" "Languages"
    "categories" {"adopt" "Clojure"
                  "trial" "Idris"
                  "assess" "Pixie"
                  "hold" "JavaScript"}
    }
   {"name" "Cute Animals"
    "categories" {"adopt" "Llamas"
                  "trial" "Alpagas"
                  "assess" "Wombats"
                  "hold" "Hedgehogs"}
    }
   {"name" "Tasty Food"
    "categories" {"adopt" "Crumpets"
                  "trial" "Mint Tim Tams"
                  "assess" "Raclette"
                  "hold" "Egg Nog"}
    }])

(deftest test-read-configuration
  (let [expected sample-configuration]
    (is (= expected (read-configuration "sample.json")))))

(deftest test-targets
  (let [expected ["Robots" "Languages" "Cute Animals" "Tasty Food"]]
    (is (= expected (targets sample-configuration)))))