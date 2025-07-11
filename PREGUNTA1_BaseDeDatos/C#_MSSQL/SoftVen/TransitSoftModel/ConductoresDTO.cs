using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftVenModel
{
    public class ConductoresDTO
    {
        private int? conductorId;
        private string paterno;
        private string materno;
        private string nombres;
        private string numLicencia;
        private int? tipoLicenciaId;
        private int? puntosAcumulados;

        public ConductoresDTO()
        {
            this.ConductorId = null;
            this.Paterno = null;
            this.Materno = null;
            this.Nombres = null;
            this.NumLicencia = null;
            this.TipoLicenciaId = null;
            this.PuntosAcumulados = null;
        }

        public ConductoresDTO(int conductorId, string paterno, string materno, string nombres, string numLicencia, int tipoLicenciaId, int puntosAcumulados)
        {
            this.ConductorId = conductorId;
            this.Paterno = paterno;
            this.Materno = materno;
            this.Nombres = nombres;
            this.NumLicencia = numLicencia;
            this.TipoLicenciaId = tipoLicenciaId;
            this.PuntosAcumulados = puntosAcumulados;
        }

        public int? ConductorId { get => conductorId; set => conductorId = value; }
        public string Paterno { get => paterno; set => paterno = value; }
        public string Materno { get => materno; set => materno = value; }
        public string Nombres { get => nombres; set => nombres = value; }
        public string NumLicencia { get => numLicencia; set => numLicencia = value; }
        public int? TipoLicenciaId { get => tipoLicenciaId; set => tipoLicenciaId = value; }
        public int? PuntosAcumulados { get => puntosAcumulados; set => puntosAcumulados = value; }
    }
}
