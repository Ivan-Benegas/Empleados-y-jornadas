import { Jornada } from "./Jornada";

export class Empleado{
    id?: number;
    nombre?: string;
    apellido?: string;
    edad?: number;
    dni?: number;
    jornadas?: Array<Jornada>;
}