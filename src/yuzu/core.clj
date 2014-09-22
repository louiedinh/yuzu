(ns yuzu.core (:require [clojure-mail.core :refer :all]
                        [clojure-mail.message :as message]))

(def messages (inbox credentials))

(def latest (message/read-message (first messages)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println latest))
