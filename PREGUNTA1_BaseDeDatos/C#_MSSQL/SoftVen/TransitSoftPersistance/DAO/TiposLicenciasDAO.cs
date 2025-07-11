using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftVenModel;

namespace SoftVenPersistance.DAO
{
    public interface TiposLicenciasDAO
    {
        int Insertar(TiposLicenciasDTO tipoLicencia);
        TiposLicenciasDTO ObtenerPorId(int tipoLicenciaId);
        BindingList<TiposLicenciasDTO> ListarTodos();
        int Modificar(TiposLicenciasDTO tipoLicencia);
        int Eliminar(TiposLicenciasDTO tipoLicencia);
    }
}
