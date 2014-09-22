(ns yuzu.core (:require [clojure.string :as string]
                        [clojure-mail.core :refer :all]
                        [clojure-mail.message :as message]))

(def credentials (map string/trim (string/split (string/trim-newline  (slurp "src/yuzu/credentials.secure")) #",")))


(print credentials)

(def messages (inbox (apply gen-store credentials)))

(def latest (message/read-message (first messages)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println latest))
