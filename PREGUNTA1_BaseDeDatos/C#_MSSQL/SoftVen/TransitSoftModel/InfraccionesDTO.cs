using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftVenModel
{
    public class InfraccionesDTO
    {
        private int? infraccionId;
        private string descripcion;
        private decimal? montoMulta;
        private string gravedad;
        private int? puntos;

        public InfraccionesDTO()
        {
            this.InfraccionId = null;
            this.Descripcion = null;
            this.MontoMulta = null;
            this.Gravedad = null;
            this.Puntos = null;
        }

        public InfraccionesDTO(int infraccionId, string descripcion, decimal montoMulta, string gravedad, int puntos)
        {
            this.InfraccionId = infraccionId;
            this.Descripcion = descripcion;
            this.MontoMulta = montoMulta;
            this.Gravedad = gravedad;
            this.Puntos = puntos;
        }

        public int? InfraccionId { get => infraccionId; set => infraccionId = value; }
        public string Descripcion { get => descripcion; set => descripcion = value; }
        public decimal? MontoMulta { get => montoMulta; set => montoMulta = value; }
        public string Gravedad { get => gravedad; set => gravedad = value; }
        public int? Puntos { get => puntos; set => puntos = value; }
    }
}
