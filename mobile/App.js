import {Component} from "react";
import {NavigationContainer} from "@react-navigation/native";
import {createNativeStackNavigator} from "@react-navigation/native-stack";
import {Home} from "./screens/Home";
import {RegisterRefereeScreen} from "./screens/RegisterRefereeScreen";
import {StartScreen} from "./screens/StartScreen";
import {Competitions} from "./screens/Competitions";
import {Rounds} from "./screens/Rounds";
import {History} from "./screens/History";
import {styles} from "./styles";

export default class App extends Component {

  render() {
    return (
        <NavigationContainer>
          <Stack.Navigator>
            <Stack.Screen name={"Start"} component={Home}/>
            <Stack.Screen name={"Wybierz zawody"} component={Competitions}/>
            <Stack.Screen name={"Wybierz rundę"} component={Rounds}/>
            <Stack.Screen name={"Starty zawodników"} component={StartScreen}/>
            <Stack.Screen name={"Zarejestruj się"} component={RegisterRefereeScreen}/>
            <Stack.Screen name={"Historia"} component={History}/>
          </Stack.Navigator>
        </NavigationContainer>
    );
  }

}

const Stack = createNativeStackNavigator();


