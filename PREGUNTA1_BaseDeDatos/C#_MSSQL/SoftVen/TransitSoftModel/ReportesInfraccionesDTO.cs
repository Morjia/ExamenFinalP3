using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftVenModel
{
    public class ReportesInfraccionesDTO
    {
        private int? reporteId;
        private int? conductorId;
        private string paterno;
        private string materno;
        private string nombres;
        private int? vehiculoId;
        private string placa;
        private string marca;
        private string modelo;
        private int? anho;
        private int? infraccionId;
        private string descripcion;
        private decimal? monto;
        private string gravedad;

        public ReportesInfraccionesDTO()
        {
            this.ReporteId = null;
            this.ConductorId = null;
            this.Paterno = null;
            this.Materno = null;
            this.Nombres = null;
            this.VehiculoId = null;
            this.Placa = null;
            this.Marca = null;
            this.Modelo = null;
            this.Anho = null;
            this.InfraccionId = null;
            this.Descripcion = null;
            this.Monto = null;
            this.Gravedad = null;
        }

        public int? ReporteId { get => reporteId; set => reporteId = value; }
        public int? ConductorId { get => conductorId; set => conductorId = value; }
        public string Paterno { get => paterno; set => paterno = value; }
        public string Materno { get => materno; set => materno = value; }
        public string Nombres { get => nombres; set => nombres = value; }
        public int? VehiculoId { get => vehiculoId; set => vehiculoId = value; }
        public string Placa { get => placa; set => placa = value; }
        public string Marca { get => marca; set => marca = value; }
        public string Modelo { get => modelo; set => modelo = value; }
        public int? Anho { get => anho; set => anho = value; }
        public int? InfraccionId { get => infraccionId; set => infraccionId = value; }
        public string Descripcion { get => descripcion; set => descripcion = value; }
        public decimal? Monto { get => monto; set => monto = value; }
        public string Gravedad { get => gravedad; set => gravedad = value; }
    }
}
