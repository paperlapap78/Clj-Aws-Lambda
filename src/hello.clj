(ns hello
  (:gen-class
   :methods [^:static [handler [String] String]]))

(defn -handler [s]
  (str "Hello " s "!"))

; Add POJO handling

(defn -handlepojo [this event]
  (str "Hello " (.getFirstName event) " " (.getLastName event)))

(gen-class
 :name PojoHandler
 :methods [[handlepojo [example.MyEvent] String]])
