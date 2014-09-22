(ns yuzu.core (:require [clojure.string :as string]
                        [clojure-mail.core :refer :all]
                        [clojure-mail.message :as message]))

(->> "src/yuzu/credentials.secure" slurp string/trim-newline (#(string/split % #",")) (map string/trim))


(print credentials)

(def messages (inbox (apply gen-store credentials)))

(def latest (message/read-message (first messages)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println latest))
