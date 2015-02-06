(ns yuzu.core (:require [clojure.string :as string]
                        [clojure-mail.core :refer :all]
                        [clojure-mail.message :as message]))

(def credentials (->> "src/yuzu/credentials.secure"
       slurp
       string/trim-newline
       (#(string/split % #","))
       (map string/trim)))

(def raw-messages (inbox (apply gen-store credentials)))

;(def messages (map message/read-message (take 10 raw-messages)))

(defn get-sender [raw-message]
  (-> raw-message .getSender .getAddress))

(defn get-senders [msgs]
  (let [batched-msgs (partition 100 msgs)]
    (loop [unprocessed batched-msgs
           senders []]
      (if (empty? unprocessed)
        'done
        (do 
          (print ".")
          (recur (rest unprocessed)
                 (concat (get-senders (first unprocessed)))))))))

(loop [nums '(1 2 3)]
  (print ".")
  (recur (rest nums)))

(defn senders-map [msgs]
  (reduce (fn [dict sender-addr]
            (assoc dict
              sender-addr
              (inc (get dict sender-addr 0))))
          {}
          (get-senders msgs)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "hello"))
