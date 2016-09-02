(ns stream-handler
  (:gen-class
   :implements [com.amazonaws.services.lambda.runtime.RequestStreamHandler])
  (:require [clojure.data.json :as json]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.pprint :refer [pprint]]))

(defn handle-event [event]
  (pprint event)
  {:who-done-it (get-in event [:records 0 :request-parameters :source-ip-address])
   :bucket-owner (get-in event [:records 0 :s3 :bucket :owner-identity :principal-id])})

(defn key->keyword [key-string]
  (-> key-string
      (s/replace #"([a-z])([A-Z])" "$1-$2")
      (s/replace #"([A-Z]+)([A-Z])" "$1-$2")
      (s/lower-case)
      (keyword)))

(defn -handleRequest [this is os context]
  (let [w (io/writer os)]
    (-> (json/read (io/reader is) :key-fn key->keyword)
        (handle-event)
        (json/write w))
    (.flush w)))
