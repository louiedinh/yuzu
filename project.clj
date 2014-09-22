(defproject yuzu "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.jsoup/jsoup "1.7.3"] ;; for cleaning up messy html messages
                 [javax.mail/mail "1.4.4"]]
  :main ^:skip-aot yuzu.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
