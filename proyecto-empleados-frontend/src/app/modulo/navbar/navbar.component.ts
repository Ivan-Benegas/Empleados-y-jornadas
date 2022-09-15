import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  paginaActual = this.router.url;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  irAHome(){
    this.router.navigate(['/']);
  }

  irAEmpleado(){
    this.router.navigate(['/empleado']);
  }

  irAJornada(){
    this.router.navigate(['/jornada']);
  }

}
