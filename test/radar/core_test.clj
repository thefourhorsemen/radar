(ns radar.core-test
  (:require [clojure.test :refer :all]
            [radar.core :refer :all]))

(deftest test-read-configuration
  (let [expected [{"name" "Robots"
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
                   }]]
    (is (= expected (read-configuration "sample.json")))))