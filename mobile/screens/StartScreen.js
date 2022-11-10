import {View, Text} from "react-native";
import {styles} from "../styles";
import axios from "axios";
import {useState, useEffect} from "react";
import {LeadStartPanel} from "./LeadStartPanel";
import {StartLoader} from "./StartLoader";

export function StartScreen ({route, navigation}) {

    const {hash, ip, starts} = route.params;
    const [disciplines, setDisciplines] = useState([]);
    const [activeParticipantPosition, setActiveParticipantPosition] = useState(0);
    let ipAddressAndPort = "http://" + ip + ":8080";

    useEffect(() => calculateDisciplines(), [starts]);

    function calculateDisciplines() {
        setDisciplines(starts.map(start => start.discipline));
    }

    function navigateTo(value) {

        if(value === 1) {
            if(activeParticipantPosition + 1 < starts.length) {
                setActiveParticipantPosition(activeParticipantPosition + 1);
            }
        }
        else {
            if(activeParticipantPosition - 1 >= 0) {
                setActiveParticipantPosition(activeParticipantPosition - 1);
            }
        }
    }

    switch (disciplines.length > 0) {
        case true:
            return (
                <StartLoader
                    startId={starts[activeParticipantPosition].id}
                    ip={ip}
                    hash={hash}
                    navigation={navigation}
                    discipline={disciplines[activeParticipantPosition]}
                    participant={starts[activeParticipantPosition].participant}
                    navigateTo={navigateTo}
                />
            );
        case false:
            return (
                <View>
                    <Text>Waiting ...</Text>
                </View>
            );
    }

}
