(defproject radar "1.0.0"
  :description "Radar generator"
  :url "https://github.com/thefourhorsemen/radar"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [clj-json "0.5.3"]
                 [quil "2.2.6"]]
  :main ^:skip-aot radar.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
