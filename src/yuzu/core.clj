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

(defn get-subject [raw-message]
  (.getSubject raw-message))

(defn get-senders [msgs]
  (loop [unprocessed msgs
         senders []]
    (if (empty? unprocessed)
      senders
      (do 
        (print ".")
        (recur (rest unprocessed)
               (cons (get-sender (first unprocessed)) senders))))))

(defn senders-map [msgs]
  (reduce (fn [dict sender-addr]
            (assoc dict
              sender-addr
              (inc (get dict sender-addr 0))))
          {}
          (get-senders msgs)))

(defn pretty-print [sender msgs]
  (print (format "%s:\n" sender))
  (doseq [msg msgs] (print "\t" (get-subject msg) "\n")))

(defn show-messages-by-sender [msgs]
  (let [msgs-by-sender (group-by get-sender msgs)]
    (doseq [[sender msgs] msgs-by-sender]
      (pretty-print sender msgs))))



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "hello"))
